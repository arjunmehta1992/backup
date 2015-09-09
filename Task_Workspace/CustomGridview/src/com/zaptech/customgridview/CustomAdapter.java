package com.zaptech.customgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter{

	
	
    Context context;
 int [] imageId;
      private static LayoutInflater inflater=null;
    public CustomAdapter(HomeActivity mainActivity, int[] prgmImages) {
        // TODO Auto-generated constructor stub
       
        context=mainActivity;
        imageId=prgmImages;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
	@Override
	public int getCount() {
		
		return imageId.length;
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	
	public class Holder
    {
        Button btn_one;
        Button btn_two;
        ImageView img;
    }
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		Holder holder=new Holder();
        View rowView;

             rowView = inflater.inflate(R.layout.raw_customgrid, null);
             holder.btn_one=(Button) rowView.findViewById(R.id.btn_First);
             holder.btn_two=(Button) rowView.findViewById(R.id.btn_Second);
             holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

       
         holder.img.setImageResource(imageId[position]);

         rowView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "You Clicked "+imageId[position], Toast.LENGTH_LONG).show();
			}
		});
        return rowView;
	}

}
