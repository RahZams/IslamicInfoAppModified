package com.example.islamicinfoapp.src.main.java.com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.islamicinfoapp.R;
import com.example.islamicinfoapp.databinding.SurahAyahItemLayoutBinding;
import com.example.islamicinfoapp.src.main.java.com.model.Constants;
import com.example.islamicinfoapp.src.main.java.com.model.SurahAyahItemApiData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SurahItemAdapter extends RecyclerView.Adapter<SurahItemAdapter.SurahItemViewHolder> {

    private List<SurahAyahItemApiData> mSurahDataAyahs;
    private Context mContext;
    int viewWidth;
    private static final String TAG = SurahItemAdapter.class.getSimpleName();

    public SurahItemAdapter(Context context,ArrayList<SurahAyahItemApiData> surahAyahItemApiData) {
        mContext = context;
        mSurahDataAyahs = surahAyahItemApiData;
    }

    @NonNull
    @Override
    public SurahItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SurahAyahItemLayoutBinding binding = DataBindingUtil.inflate(inflater,R.layout.surah_ayah_item_layout,parent,false);
        return new SurahItemViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull SurahItemViewHolder holder, int position) {
        SpannableString spanString;
        Log.d(Constants.SURAHITEM, TAG + " onBindViewHolder: " + position);
        //holder.itemView.setSurahitem(mSurahDataAyahs.get(position));
        Log.d(Constants.SURAHITEM, TAG + " onBindViewHolder: " + mSurahDataAyahs.get(position).getAyahText().length());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            spanString = new SpannableString(mSurahDataAyahs.get(position).getAyahText() + "      " +
                     Html.fromHtml("\uFD3F",Html.FROM_HTML_MODE_LEGACY) +
                    NumberFormat.getInstance(new Locale("ar","SA")).
                            format(mSurahDataAyahs.get(position).getNumberInSurah()) +
                    Html.fromHtml("\uFD3E",Html.FROM_HTML_MODE_LEGACY));
        }
        else {
            spanString = new SpannableString(mSurahDataAyahs.get(position).getAyahText() + "      " +
                    Html.fromHtml("\uFD3F") +
                    NumberFormat.getInstance(new Locale("ar","SA")).
                            format(mSurahDataAyahs.get(position).getNumberInSurah()) +
                    Html.fromHtml("\uFD3E"));
        }

        spanString.setSpan(new ForegroundColorSpan(Color.BLACK),
                spanString.length()-3,spanString.length(),SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        SpannableString spanString = new SpannableString(mSurahDataAyahs.get(position).getAyahText() + " - " +
//                "  " + NumberFormat.getNumberInstance(new Locale("ar","SA"))
//                        .format(mSurahDataAyahs.get(position).getNumberInSurah()));
//                  NumberFormat.getNumberInstance(Locale.forLanguageTag("AR"))
//                        .format(mSurahDataAyahs.get(position).getNumberInSurah()) + " \u06DD");
        //SpannableString spanString = new SpannableString("ndndbjdsbjsduidgfudsufgdsugfuisdhfuidhfuirhguihgugbuewiiiwiwieuew8eugfbdbdufuefh");
//        builder.append(mSurahDataAyahs.get(position).getAyahText());
//        builder.append("   ");
//        Log.d("surah_item", "onBindViewHolder:before adding number " + builder.length()
//         + String.valueOf(mSurahDataAyahs.get(position).getNumberInSurah()).length());
//        builder.append(String.valueOf(mSurahDataAyahs.get(position).getNumberInSurah()));
        //int start = builder.length();
        //Log.d("surah_item", "onBindViewHolder:builder after adding " + builder.charAt(builder.length() - 1));
//        spanString.setSpan(new RoundedBackgroundSpan(mContext.getResources().getDrawable(R.drawable.circle)),spanString.length()-2,
//                spanString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        builder.setSpan(new RoundedBackgroundSpan(mContext.getResources().getDrawable(R.drawable.circle)),String.valueOf(mSurahDataAyahs.get(position).getNumberInSurah()).length() + 1,
//                String.valueOf(mSurahDataAyahs.get(position).getNumberInSurah()).length() + 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        Spannable spanString = new SpannableString(String.valueOf(mSurahDataAyahs.get(position).getNumberInSurah()));
//        Log.d("surah_item", "onBindViewHolder: string length" + spanString.length());
//        spanString.setSpan(new RoundedBackgroundSpan(mContext.getResources().getDrawable(R.drawable.circle)),
//                0,spanString.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        Rect bounds = new Rect();
//        Paint textPaint = holder.itemView.ayahText.getPaint();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            textPaint.getTextBounds(spanString,0,spanString.length(),bounds);
//        }
//        int height = bounds.height();
//        int width = bounds.width();

//        final Rect bounds = new Rect();
//        final Paint paint = new Paint();
//        paint.setTextSize(100);
//        paint.getTextBounds(spanString, 0, spanString.length(), bounds);
//        final int numlines = (int) Math.ceil(bounds.width())/100;
        holder.itemView.ayahText.setText(spanString,TextView.BufferType.SPANNABLE);

        // onpredraw should be used if the draw code is used based on line count
//        holder.itemView.ayahText.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                int linecount = holder.itemView.ayahText.getLineCount();
//                Log.d(Constants.SURAHITEM, TAG + " onPreDraw: " + linecount);
//                // use this commented line to give background as circle to spannablestring
////                spanString.setSpan(new RoundedBackgroundSpan(mContext.getResources().getDrawable(R.drawable.circle),
////                                linecount),spanString.length()-2,
////                        spanString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
////                spanString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent)),
////                        spanString.length()-2,spanString.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
////                spanString.setSpan(new DesignBackgroundSpan("\u06DD"),spanString.length()-2,
////                        spanString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                holder.itemView.ayahText.setText("");
//                holder.itemView.ayahText.setText(spanString,TextView.BufferType.SPANNABLE);
//                holder.itemView.ayahText.setVisibility(View.VISIBLE);
//                return true;
//            }
//        });
        Log.d(Constants.SURAHITEM, TAG + " onBindViewHolder: " + "line count:" +
                holder.itemView.ayahText.getLineCount());
//        Rect bounds = new Rect();
//        Paint textPaint = holder.itemView.ayahText.getPaint();
//        textPaint.getTextBounds(spanString,0,spanString.length(),bounds);
//        //int height = bounds.height();
//        int width = bounds.width();
//
//        final Rect bounds1 = new Rect();
//        final Paint paint1 = new Paint();
//        paint1.setTextSize(width);
//        paint1.getTextBounds(spanString, 0, spanString.length(), bounds1);
//
//        int numLines = (int) (Math.ceil(bounds1.width())/width);
//        Log.d("surah_item ", "onBindViewHolder: numlines" + numLines);

//        ViewTreeObserver viewTreeObserver = holder.itemView.getRoot().getViewTreeObserver();
//        if (viewTreeObserver.isAlive()) {
//            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    holder.itemView.getRoot().getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                    viewWidth = holder.itemView.getRoot().getWidth();
//                    int lines  = spanString.length()/viewWidth;
//                    Log.d("surah_item", "onGlobalLayout: " + viewWidth + "lines" + lines);
//                }
//            });
//        }

        //holder.itemView.ayahText.setText(spanString,TextView.BufferType.SPANNABLE);
//        spanString.setSpan(new RoundedBackgroundSpan(mContext.getResources().getDrawable(R.drawable.circle),
//                        holder.itemView.ayahText.getLineCount()),spanString.length()-2,
//                spanString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        holder.itemView.ayahText.setText("");
//        holder.itemView.ayahText.setText(spanString,TextView.BufferType.SPANNABLE);
//        holder.itemView.ayahText.setVisibility(View.VISIBLE);
        //holder.itemView.setSurahitem(mSurahDataAyahs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSurahDataAyahs.size();
    }

    public void updateList(List<SurahAyahItemApiData> surahDataAyahs) {
        Log.d(Constants.SURAHITEM, TAG + " updateList: " + surahDataAyahs.size());
        mSurahDataAyahs = surahDataAyahs;
        notifyDataSetChanged();
    }



    public class SurahItemViewHolder extends RecyclerView.ViewHolder{

        public SurahAyahItemLayoutBinding itemView;

        public SurahItemViewHolder(@NonNull SurahAyahItemLayoutBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

    public class DesignBackgroundSpan extends ReplacementSpan{
        String uniChar;

        public DesignBackgroundSpan(String unicodeChar) {
            uniChar = unicodeChar;
        }

        @Override
        public int getSize(@NonNull Paint paint, CharSequence text, int start, int end,
                           @Nullable Paint.FontMetricsInt fm) {
            return Math.round(paint.measureText(text, start, end));
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end,
                         float x, int top, int y, int bottom, @NonNull Paint paint) {
            float textSize = paint.getTextSize();
            canvas.drawText(uniChar, 0, 1, x - textSize / 5, (float) y, paint);
            paint.setTextSize(textSize - textSize / 3);
            canvas.drawText(text, start, end, x, y - textSize / 9, paint);
            paint.setTextSize(textSize);
            paint.setTextAlign(Paint.Align.CENTER);
        }
    }

    public class RoundedBackgroundSpan extends ReplacementSpan
    {
        Drawable mCircle;
        int mLineCount;
        public RoundedBackgroundSpan(Drawable drawable, int lineCount) {
            mLineCount = lineCount;
            mCircle = drawable;
        }

        @Override
        public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
//            final Paint.FontMetrics paintFontMetrics = paint.getFontMetrics();
//            if (fm != null) {
//                fm.ascent = (int) paintFontMetrics.ascent;
//                fm.bottom = (int) paintFontMetrics.bottom;
//                fm.descent = (int) paintFontMetrics.descent;
//                fm.leading = (int) paintFontMetrics.leading;
//                fm.top = (int) paintFontMetrics.top;
//            }
            int textwidth = Math.round(measureText(paint, text, start, end));
            //Log.d("surah_item", "getSize: " + "text " + text + "width " + textwidth);
            return Math.max(textwidth,mCircle.getIntrinsicWidth());
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                         int top, int y, int bottom, @NonNull Paint paint) {
            Log.d(Constants.SURAHITEM, TAG + " draw:" + "text " +  text + "length " + text.length() +
                    "count " + mLineCount);
            Rect bounds = new Rect();
//            if (text.length() > Integer.valueOf(mContext.getResources().getString(R.string.min_text_length_surah)))
//                mCircle.setBounds((int) x, top + 20, (int) x + mCircle.getIntrinsicWidth(),
//                        top + 20 + mCircle.getIntrinsicHeight());
//            else if (text.length() <= Integer.valueOf(mContext.getResources().getString(R.striholder.itemView.ayahText.setVisibility(View.VISIBLE);czng.min_text_length_surah)))
//                mCircle.setBounds((int) x, top + 85, (int) x + mCircle.getIntrinsicWidth(),
//                        top + 85 + mCircle.getIntrinsicHeight());
            if (mLineCount > 1)
                mCircle.setBounds((int) x, top + 18, (int) x + mCircle.getIntrinsicWidth(),
                       top + 18 + mCircle.getIntrinsicHeight());
            else if(mLineCount == 1)
                mCircle.setBounds((int) x, top + 72, (int) x + mCircle.getIntrinsicWidth(),
                        top + 72 + mCircle.getIntrinsicHeight());
            mCircle.draw(canvas);
            paint.setColor(mContext.getResources().getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(45);
            paint.getTextBounds(text.toString(), 0, text.subSequence(start, end).length(), bounds);
            canvas.drawText(text, start, end, x + mCircle.getIntrinsicWidth()/2f ,
                    y + 2f, paint);
            //canvas.drawText(text, start, end, x , y, paint);

//            RectF rect = new RectF(x, top + 40, x + text.length(), top + 150);
//            paint.setColor(Color.BLACK);
//            paint.setStyle(Paint.Style.STROKE);
//            canvas.drawRoundRect(rect, 30, 30, paint);
//
//            paint.setColor(Color.GREEN);
//            canvas.drawText(text, start, end, x, y, paint);
        }

        private float measureText(Paint paint, CharSequence text, int start, int end)
        {
            return paint.measureText(text, start, end);
        }
    }

//    public class RoundedBackgroundSpan extends ReplacementSpan
//    {
//        Drawable mCircle;
//        public RoundedBackgroundSpan(Drawable drawable) {
//            mCircle = drawable;
//        }
//
//        @Override
//        public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
//            int textwidth = Math.round(measureText(paint, text, start, end));
//            Log.d("surah_item", "getSize: " + textwidth);
//            return Math.max(textwidth,mCircle.getIntrinsicWidth());
//        }
//
//        @Override
//        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
//            Log.d("surah_item", "draw: " + "text" + text + "start" +start);
//            Rect bounds = new Rect();
//            mCircle.setBounds((int)x,top + 40,(int)x+mCircle.getIntrinsicWidth(),top + 40 + mCircle.getIntrinsicHeight());
//            mCircle.draw(canvas);
//            //paint.setColor(Color.BLACK);
//            paint.setStyle(Paint.Style.FILL_AND_STROKE);
//            paint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
//            //paint.setStyle(Paint.Style.FILL);
//            paint.setTextAlign(Paint.Align.CENTER);
//            paint.setTextSize(5);
//            Log.d("surah_item", "draw: " + text.subSequence(start, end));
//            paint.getTextBounds(text.toString(), 0, text.subSequence(start, end).length(), bounds);
//            canvas.drawText(text, start, end, x + mCircle.getIntrinsicWidth()/2f , y, paint);
//            //canvas.drawText(text, start, end, x , y, paint);
//
////            RectF rect = new RectF(x, top + 40, x + text.length(), top + 150);
////            paint.setColor(Color.BLACK);
////            paint.setStyle(Paint.Style.STROKE);
////            canvas.drawRoundRect(rect, 30, 30, paint);
////            paint.setColor(Color.GREEN);
////            canvas.drawText(text, start, end, x, y, paint);
//        }
//
//        private float measureText(Paint paint, CharSequence text, int start, int end)
//        {
//            return paint.measureText(text, start, end);
//        }
//    }
}
