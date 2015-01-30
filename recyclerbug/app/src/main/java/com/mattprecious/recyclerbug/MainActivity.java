package com.mattprecious.recyclerbug;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class MainActivity extends Activity {
  private final Adapter adapter = new Adapter();

  private SwipeRefreshLayout swipeRefreshLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    RecyclerView recyclerView = new RecyclerView(this);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);

    swipeRefreshLayout = new SwipeRefreshLayout(this);
    swipeRefreshLayout.addView(recyclerView);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
      }
    });

    setContentView(swipeRefreshLayout);
  }

  private static class Adapter extends RecyclerView.Adapter<ViewHolder> {

    @Override public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      return new ViewHolder(
          LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false));
    }

    @Override public void onBindViewHolder(ViewHolder viewHolder, int i) {
      viewHolder.textView.setText(String.valueOf(i));
    }

    @Override public int getItemCount() {
      return 50;
    }
  }

  private static class ViewHolder extends RecyclerView.ViewHolder {
    public final TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);
      this.textView = (TextView) itemView.findViewById(R.id.text);
    }
  }
}
