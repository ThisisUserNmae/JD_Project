package com.bwei.jd_project.mvp.home.view.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup{

        private static final String TAG = "FlowLayout------";
        public FlowLayout(Context context) {
            super(context);
        }
        public FlowLayout(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }
        public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            measureChildren(widthMeasureSpec, heightMeasureSpec);
            Log.d(TAG, "onMeasure: ");
            //最大的宽度,也就是FlowLayout父布局的宽度   获取两种模式
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int width = 0;
            int height = 0;
            int childWidth;
            int childHeight;
            View childView;
            int lineWidth = 0;
            int lineHeight = 0;
            int totalHeight = 0;
            MarginLayoutParams layoutParams;
            int leftMargin;
            int rightMargin;
            int topMargin;
            int bottomMargin;
            for (int i = 0; i < getChildCount(); i++) {
                childView = getChildAt(i);
                childWidth = childView.getMeasuredWidth();
                layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                leftMargin = layoutParams.leftMargin;
                rightMargin = layoutParams.rightMargin;
                topMargin = layoutParams.topMargin;
                bottomMargin = layoutParams.bottomMargin;
                if (childWidth > widthSize) {
                    throw new IllegalArgumentException("子view宽度不能大于FlowLayout宽度");
                }
                childHeight = childView.getMeasuredHeight();
                if (lineWidth + childWidth + leftMargin + rightMargin > widthSize + getPaddingLeft() - getPaddingRight()) {
                    //换行
                    //只要出现换行的情况,那就说明当前的行宽已经不够用了
                    width = widthSize;
                    //totalHeight不包含最后一行的高度
                    totalHeight += lineHeight;
                    lineHeight = childHeight + topMargin + bottomMargin;
                    lineWidth = childWidth + leftMargin + rightMargin;

                } else {
                    //不换行
                    lineWidth += childWidth + leftMargin + rightMargin;
                    //当前行的高度
                    lineHeight = Math.max(lineHeight, childHeight);
                    //如果不换行,那测量的宽度就是当前行的宽度,如果换行就取最大的宽度
                    width = Math.max(width, lineWidth);
                }
                //当结束遍历的时候要加上最后一行的高度
                if (i == getChildCount() - 1) {
                    totalHeight += lineHeight;
                    height = totalHeight;
                }
                width = widthMode == MeasureSpec.EXACTLY ? widthSize : width+getPaddingLeft()+getPaddingRight();
                height = heightMode == MeasureSpec.EXACTLY ? heightSize : height+getPaddingBottom()+getPaddingTop();
                //确定最终测量的宽度
                setMeasuredDimension(width, height);
            }

        }
        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            Log.d(TAG, "onLayout: ");
            View childView;
            int childWidth;
            int childHeight;
            int lineWidth = 0;
            int lineHeight = 0;
            int totalHeight = 0;
            MarginLayoutParams layoutParams;
            int leftMargin;
            int rightMargin;
            int topMargin;
            int bottomMargin;
            for (int i = 0; i < getChildCount(); i++) {
                childView = getChildAt(i);
                childWidth = childView.getMeasuredWidth();
                childHeight = childView.getMeasuredHeight();
                layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                leftMargin = layoutParams.leftMargin;
                rightMargin = layoutParams.rightMargin;
                topMargin = layoutParams.topMargin;
                bottomMargin = layoutParams.bottomMargin;
                if (lineWidth + childWidth + leftMargin + rightMargin > getMeasuredWidth() - getPaddingRight() - getPaddingLeft()) {
                    //换行
                    //totalHeight不包含最后一行的宽度
                    totalHeight += lineHeight;
                    lineWidth = 0;
                    layoutChildView(childView, lineWidth + leftMargin, totalHeight, lineWidth + childWidth + rightMargin, totalHeight + childHeight);
                    //换行宽就是最大宽
                    lineHeight = childHeight + topMargin + bottomMargin;
                    lineWidth = childWidth + rightMargin + leftMargin;

                } else {
                    //不换行
                    layoutChildView(childView, lineWidth + leftMargin, totalHeight, lineWidth + childWidth + rightMargin, totalHeight + childHeight);
                    lineWidth += childWidth + leftMargin + rightMargin;
                    //当前行的高度
                    lineHeight = Math.max(lineHeight, childHeight + topMargin + bottomMargin);
                }
            }

        }
        public void layoutChildView(View child, int l, int t, int r, int b) {
            l+=getPaddingLeft();
            t+=getPaddingLeft();
            r+=getPaddingLeft();
            b+=getPaddingLeft();
            child.layout(l, t, r, b);
        }
        @Override
        protected LayoutParams generateDefaultLayoutParams() {
            return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        }
        @Override
        protected LayoutParams generateLayoutParams(LayoutParams p) {
            return new MarginLayoutParams(p);
        }
        @Override
        public LayoutParams generateLayoutParams(AttributeSet attrs) {
            return new MarginLayoutParams(getContext(),attrs);
        }
    }

