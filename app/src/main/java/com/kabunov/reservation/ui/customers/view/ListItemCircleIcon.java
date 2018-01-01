package com.kabunov.reservation.ui.customers.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kabunov.reservation.R;

public class ListItemCircleIcon extends FrameLayout {
	private ImageView mBackground;
	private TextView mLetter;

	public ListItemCircleIcon(final Context context) {
		super(context);
	}

	public ListItemCircleIcon(final Context context, final AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ListItemCircleIcon(final Context context, final AttributeSet attrs, final int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		View.inflate(getContext(), R.layout.layout_list_item_circle_icon, this);

		mBackground = findViewById(R.id.circle_icon_back);
		mLetter = findViewById(R.id.circle_icon_letter);
	}

	public void setBackground(final int resource) {
		mBackground.setColorFilter(ContextCompat.getColor(getContext(), resource), android.graphics.PorterDuff.Mode.SRC_IN);
	}

	public void setLetter(final String letter) {
		mLetter.setText(letter.toUpperCase());
	}
}
