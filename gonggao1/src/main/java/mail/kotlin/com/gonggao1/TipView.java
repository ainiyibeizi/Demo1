package mail.kotlin.com.gonggao1;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Administrator on 2015/12/8.
 */
public class TipView extends FrameLayout {

    /**  动画间隔  */
    private static final int ANIM_DELAYED_MILLIONS = 1 * 1000;
    /**  动画持续时长  */
    private static final int ANIM_DURATION = 2 * 1000;
    /**  默认字体颜色  */
    private static final String DEFAULT_TEXT_COLOR = "#2F4F4F";
    /**  默认字体大小  dp  */
    private static final int DEFAULT_TEXT_SIZE = 16;
    private Animation anim_out, anim_in;

    /**  循环播放的消息  */
    private List<String> tipList;
    /**  当前轮播到的消息索引  */
    private int curTipIndex = 0;
    private long lastTimeMillis ;
    private TextView tv_tip_out,tv_tip_out1;
    //private ImageView iv_gonggao;
    private View mView;
    private TextView tv_tip_in;

    public TipView(Context context) {
        this(context, null);
    }

    public TipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTipFrame(context);
        initAnimation();
    }

    private void initTipFrame(Context context) {
        //初始化布局
        mView = TipView.inflate(context, R.layout.gonggao_item, null);
        tv_tip_out= mView.findViewById(R.id.tv_gonggao);
        tv_tip_out1=mView.findViewById(R.id.tv_gonggao1);
        //iv_gonggao= mView.findViewById(R.id.iv_gonggao);
        tv_tip_in = newTextView();
        //把他们放到view中
        addView(tv_tip_in);
        addView(mView);
    }

    /**
     *  设置要循环播放的信息
     * @param tipList
     */
    public void setTipList(List<String> tipList) {
        this.tipList = tipList;
        curTipIndex = 0;
        //将来传过来的list集合不一定只是文字也许是个类，所以到时候初始化好布局进行更新
        updateTip(tv_tip_out);
        updateTip(tv_tip_out1);
        updateTipAndPlayAnimation();
    }

    private void initAnimation() {
        anim_out = newAnimation(0, -1);
        anim_in = newAnimation(1, 0);
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();
            }
        });
    }

    private void updateTipAndPlayAnimation() {

            updateTip(tv_tip_out);
        updateTip(tv_tip_out1);
        tv_tip_in.startAnimation(anim_out);
        //布局移入移出
        mView.startAnimation(anim_in);
        this.bringChildToFront(tv_tip_in);

    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000 ) {
            return ;
        }
        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }
//将来也许传的是多个类型
    private void updateTip(TextView tipView) {

        String tip = getNextTip();
        if(!TextUtils.isEmpty(tip)) {
            tipView.setText(tip);
        }
    }

    /**
     *  获取下一条消息
     * @return
     */
    private String getNextTip() {
        if (isListEmpty(tipList)) return null;
        return tipList.get(curTipIndex++ % tipList.size());
    }
    private TextView newTextView(){
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        //textView.setCompoundDrawablePadding(10);
        //textView.setGravity(Gravity.CENTER_VERTICAL);
        //textView.setLines(2);
        //textView.setEllipsize(TextUtils.TruncateAt.END);
        //textView.setTextColor(Color.parseColor(DEFAULT_TEXT_COLOR));
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE);
        return textView;
    }

    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,fromYValue,Animation.RELATIVE_TO_SELF, toYValue);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    /**
     *  将资源图片转换为Drawable对象
     * @param ResId
     * @return
     */
   /* private Drawable loadDrawable(int ResId) {
        Drawable drawable = getResources().getDrawable(ResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth() - 10, drawable.getMinimumHeight() - 10);
        return drawable;
    }*/

    /**
     *  list是否为空
     * @param list
     * @return true 如果是空
     */
    public static boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
