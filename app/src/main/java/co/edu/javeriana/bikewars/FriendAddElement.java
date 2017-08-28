package co.edu.javeriana.bikewars;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;


public class FriendAddElement extends ConstraintLayout {

    private TextView name;
    private Button message;
    private Button delete;


    public FriendAddElement(Context context){
        super(context);
        initializeViews(context);
    }

    public FriendAddElement(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public FriendAddElement(Context context,
                            AttributeSet attrs,
                            int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.friend_add_layout, this);
    }

}
