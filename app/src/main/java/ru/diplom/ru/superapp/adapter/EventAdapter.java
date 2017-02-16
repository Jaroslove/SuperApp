package ru.diplom.ru.superapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.diplom.ru.superapp.MainActivity;
import ru.diplom.ru.superapp.R;
import ru.diplom.ru.superapp.model.Event;

/**
 * Created by 1 on 17.02.2017.
 */

public class EventAdapter extends BaseAdapter {

    private List<Event> list;
    private LayoutInflater layoutInflater;

    public EventAdapter(Context context, List<Event> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_main, parent, false);
        }
        Event event = getEvent(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(event.toString());

        return view;
    }

    private Event getEvent(int position) {
        return (Event) getItem(position);
    }
}
