package co.edu.javeriana.bikewars;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FriendElement extends ConstraintLayout {

    private TextView name;
    private Button message;
    private Button delete;


    public FriendElement(Context context){
        super(context);
        initializeViews(context);
    }

    public FriendElement(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public FriendElement(Context context,
                       AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.friend_layout, this);
    }

}
