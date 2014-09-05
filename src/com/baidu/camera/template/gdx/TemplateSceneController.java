package com.baidu.camera.template.gdx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.baidu.camera.app.CameraApp;
import com.baidu.camera.template.db.TemplateSceneManager;
import com.baidu.camera.template.module.TemplateScene;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmengrong on 14-9-3.
 */
public class TemplateSceneController extends GestureDetector {

    public static final int GROUP_SIZE = 3;
    private static final int PRE = 0;
    private static final int CURR = 1;
    private static final int NEXT = 2;
    private static final String TAG = "TemplateSceneController";
    public static final int DURATION = 500;
    private List<Group> mAllGroups = new ArrayList<Group>();
    private Group[] mCurrGroups = new Group[GROUP_SIZE];
    private int mCurrPosition;
    private static TemplateSceneController instance;
    private int mDownX;
    private boolean mCanMove;
    private int mTouchedDistance;
    private ValueAnimator mTranslateAnimator;

    private TemplateSceneController(GestureListener listener) {
        super(listener);
        TemplateGestureListener gestureListener = (TemplateGestureListener) listener;
        gestureListener.setPageChangeListener(this);
    }

    public static TemplateSceneController getInstance() {
        if (instance == null) {
            instance = new TemplateSceneController(new TemplateGestureListener());
        }
        return instance;
    }

    public void initTemplates() {
        try {
            List<TemplateScene> templateScenes = TemplateSceneManager.getInstance().findAll();
            if (templateScenes != null && templateScenes.size() > 0)
            for (TemplateScene templateScene : templateScenes) {
                mAllGroups.add(templateScene.getGroup());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (mAllGroups.size() > 0) {
            updatePosition(0);
        }
    }

    public void setTemplateScene(TemplateScene templateScene) {
        Group group = templateScene.getGroup();
        if (mAllGroups.contains(group)) {
            int centerIndex = mAllGroups.indexOf(group);
            onPageChange(centerIndex);
        }
    }

    private void updatePosition(int position) {
        if (position < mAllGroups.size()) {
            if (position == 0) {
                mCurrGroups[PRE] = null;
                mCurrGroups[CURR] = mAllGroups.get(0);
                mCurrGroups[NEXT] = mAllGroups.get(1);
            } else if (position == mAllGroups.size()-1){
                mCurrGroups[PRE] = mAllGroups.get(position-1);
                mCurrGroups[CURR] = mAllGroups.get(position);
                mCurrGroups[NEXT] = null;
            } else {
                mCurrGroups[PRE] = mAllGroups.get(position-1);
                mCurrGroups[CURR] = mAllGroups.get(position);
                mCurrGroups[NEXT] = mAllGroups.get(position+1);;
            }
            mCurrPosition = position;
            initItemPosition();
            Log.v(TAG, "mCurrPosition = " + mCurrPosition);
        }
    }

    private void initItemPosition() {
        if (hasPre()) {
            getPreItem().setPosition(-CameraApp.sScreenWidth, 0);
        }
        if (hasNext()) {
            getNextItem().setPosition(CameraApp.sScreenWidth, 0);
        }
        getCurrItem().setPosition(0, 0);
    }

    public void updateItemPosition(int distance) {
        if (hasPre()) {
            Group preItem = getPreItem();
            preItem.setPosition(distance - CameraApp.sScreenWidth, 0);
        }
        if (hasNext()) {
            Group nextItem = getNextItem();
            nextItem.setPosition(distance + CameraApp.sScreenWidth, 0);
        }
        Group currItem = getCurrItem();
        currItem.setPosition(distance, 0);
    }

    public void touchUp() {
        final int centerIndex = getCenterIndex();
        onPageChange(centerIndex);
    }

    private void updateAllPosition(int centerIndex) {
        switch (centerIndex) {
            case PRE:
                if (mCurrGroups[NEXT] != null) {
                    mCurrGroups[NEXT].remove();
                }
                updatePosition(mCurrPosition-1);
                break;
            case CURR:
                updatePosition(mCurrPosition);
                break;
            case NEXT:
                if (mCurrGroups[PRE] != null) {
                    mCurrGroups[PRE].remove();
                }
                updatePosition(mCurrPosition+1);
                break;
        }
    }

    private int getCenterIndex() {
        float tempDistance = -1;
        int index = -1;
        for (int i = 0;i < mCurrGroups.length;i ++) {
            Group group = mCurrGroups[i];
            if (group != null) {
                float x = group.getX();
                float distance = Math.abs(x);
                if (tempDistance == -1 || tempDistance > distance) {
                    tempDistance = distance;
                    index = i;
                }
            }
        }
        return index;
    }

    public Group getCurrItem() {
        return mCurrGroups[1];
    }

    public Group getPreItem() {
        return mCurrGroups[0];
    }

    public Group getNextItem() {
        return mCurrGroups[2];
    }

    public boolean hasNext() {
        return mCurrGroups[2] != null;
    }

    public boolean hasPre() {
        return mCurrGroups[0] != null;
    }

    public boolean goNext() {
        if (hasNext()) {
            updatePosition(mCurrPosition+1);
            return true;
        }
        return false;
    }

    public boolean goPre() {
        if (hasPre()) {
            updatePosition(mCurrPosition-1);
            return true;
        }
        return false;
    }

    public Group[] getGroupForDraw() {
        return mCurrGroups;
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (mTranslateAnimator != null && mTranslateAnimator.isRunning()) {
            CameraApp.getInstance().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    mTranslateAnimator.cancel();
                }
            });
        }
        mDownX = x;
        mCanMove = false;
        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        mTouchedDistance = (x - mDownX);
        if (!mCanMove) {
            mCanMove = Math.abs(mTouchedDistance) > 50;
        }
        if(mCanMove) {
            updateItemPosition(mTouchedDistance);
        }
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (!super.touchUp(x, y, pointer, button)) {
            mTouchedDistance = x - mDownX;
            touchUp();
        }
        return false;
    }

    public void onPageChange(final int centerIndex,final float flingSpeed) {
        CameraApp.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                mTranslateAnimator = new ValueAnimator();
                long duration = (long) (DURATION*(Math.abs(mCurrGroups[centerIndex].getX())/CameraApp.sScreenWidth));
                mTranslateAnimator.setDuration(duration);
                mTranslateAnimator.setFloatValues(mCurrGroups[centerIndex].getX(), 0);
                mTranslateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (Float) animation.getAnimatedValue();
                        updateItemPosition((int) animatedValue);
                    }
                });
                float speed = flingSpeed;
                if (speed == -1) {
                    speed = 1000;
                }
                Log.v(TAG,"speed = " + speed);
                mTranslateAnimator.setInterpolator(new DecelerateInterpolator(speed / 1000));
                mTranslateAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        updateAllPosition(centerIndex);
                    }
                });
                mTranslateAnimator.start();
            }
        });
    }

    public void onPageChange(final int centerIndex) {
        onPageChange(centerIndex,-1);
    }

    public static class TemplateGestureListener implements GestureDetector.GestureListener {

        private TemplateSceneController mController;

        public TemplateGestureListener() {
        }

        public void setPageChangeListener(TemplateSceneController listener) {
            mController = listener;
        }

        @Override
        public boolean touchDown(float v, float v2, int i, int i2) {
            return false;
        }

        @Override
        public boolean tap(float v, float v2, int i, int i2) {
            return false;
        }

        @Override
        public boolean longPress(float v, float v2) {
            return false;
        }

        @Override
        public boolean fling(float v, float v2, int i) {
            if (Math.abs(v) > 2000) {
                if (v > 0 && mController.hasPre()) {
                    mController.onPageChange(PRE,Math.abs(v));
                } else if (v < 0 && mController.hasNext()){
                    mController.onPageChange(NEXT,Math.abs(v));
                } else {
                    mController.onPageChange(CURR,Math.abs(v));
                }
                return true;
            }
            return false;
        }

        @Override
        public boolean pan(float v, float v2, float v3, float v4) {
            return false;
        }

        @Override
        public boolean panStop(float v, float v2, int i, int i2) {
            return false;
        }

        @Override
        public boolean zoom(float v, float v2) {
            return false;
        }

        @Override
        public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
            return false;
        }
    }
}
