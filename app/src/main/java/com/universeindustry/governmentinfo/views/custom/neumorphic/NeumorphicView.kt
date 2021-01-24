package com.universeindustry.governmentinfo.views.custom.neumorphic


import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.universeindustry.governmentinfo.R
import com.universeindustry.governmentinfo.databinding.CustomNeumophicBinding
import com.universeindustry.governmentinfo.utils.extensions.ScreenManager
import kotlin.math.max


class NeumorphicView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr,defStyleRes) {

    /*
    https://m.blog.naver.com/PostView.nhn?blogId=mym0404&logNo=221397760194&categoryNo=46&proxyReferer=&proxyReferer=https:%2F%2Fwww.google.com%2F
    Property Animation :
    특정 객체의 속성을 대부분 어떤 방식으로든 강력하게 변경시킬 수 있는 프레이뭐크
    이는 특정 객체의  height, color등 특정 값들을 지속시간에 맞게 어떠한 방식으로든 변경할 수 있다.

    1. Duration
        - 지속 시간이다.
    2. Time interpolation
        - 애니메이션이 현재 시간에 대해 어떤 식으로 작동하는 지를 결정한다.
    3. Repeat count and behavior
        - 반복 횟수와 예를 들어 거꾸로 재생되는 behavior를 지정할 수 있다.
    4. Animator set
        - 여러개의 애니메이션을 같이 실행시키거나 연속적으로 실행시킬 수 있다.
    5. Frame refresh delay
        - 기본적으로 10ms로 설정되어있는 애니메이션의 프레임 refresh 시간을 지정할 수 잇다.
          그러나 이는 시스템이 바쁜지에 대해서 많은 의존성을 띈다.

    ValueAnimator :
    애니메이션의 현재 시간을 갖고 있으며, 현재 시간의 property의 값, 지속 시간 등에 대한 정보를 갖는다.

    TimeInterpolator : 시간이 예를 들어 linear하게 가거나, non-linear하게 특정 구간에서 빠르게 갈 것인지를 결정한다.

    TypeEvaluator : Property의 값을 어떤 자료형으로 계산할 것인지를 결정한다.
     */
//    init {
//        if (attrs != null) {
//            // obtainStyledAttributes 함수는 Context의 Theme에서 Style로 지정한 속성 정보 리스트를 가져오는 역할을 합니다
//            val loadAttributes = context.obtainStyledAttributes(attrs, R.styleable.NeumorphicFrameLayout)
//            val type = loadAttributes.getString(R.styleable.NeumorphicFrameLayout_shape) ?: Shape.RECTANGLE
//            if (type != null) {
//                when (type) {
//                    "0" -> shape = Shape.RECTANGLE
//                    "1" -> shape = Shape.CIRCLE
//                }
//            }
//            val stateType = loadAttributes.getString(R.styleable.NeumorphicFrameLayout_state) ?: State.FLAT
//            if (stateType != null) {
//                when (stateType) {
//                    "0" -> state = State.FLAT
//                    "1" -> state = State.CONCAVE
//                    "2" -> state = State.CONVEX
//                    "3" -> state = State.PRESSED
//                }
//            }
//            bgColor = loadAttributes.getColor(R.styleable.NeumorphicFrameLayout_background_color, Color.WHITE) ?: Color.WHITE
//            brightColor = manipulateColor(bgColor, 1.1f)
//            dimColor = manipulateColor(bgColor, 0.9f)
//            cornerRadius = loadAttributes.getDimensionPixelSize(R.styleable.NeumorphicFrameLayout_corner_radius, 0).toFloat()
//
//            loadAttributes.recycle()
//        }
//        level = calculateLevel()
//    }
//
//
//    val delegate: RoundViewDelegate = RoundViewDelegate(this, context, attrs!!)
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        w = measuredWidth.toFloat()
//        h = measuredHeight.toFloat()
//        reset()
//
//        if (delegate.isWidthHeightEqual() && width > 0 && height > 0) {
//            val max = max(width, height)
//            val measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY)
//            super.onMeasure(measureSpec, measureSpec)
//            return
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }
//
//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//        when {
//            delegate.isRadiusHalfHeight() -> delegate.setCornerRadius(height / 2)
//            delegate.isRadiusHalfHeightLeft() -> delegate.setCornerRadiusLeft(height / 2)
//            delegate.isRadiusHalfHeightRight() -> delegate.setCornerRadiusRight(height / 2)
//            delegate.isRadiusHalfWidthTop() -> delegate.setCornerRadiusTop(width / 2)
//            delegate.isRadiusHalfWidthBottom() -> delegate.setCornerRadiusBottom(width / 2)
//            else -> delegate.setBgSelector()
//        }
//    }

}