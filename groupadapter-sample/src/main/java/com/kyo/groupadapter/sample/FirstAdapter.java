/**
 * Copyright 2015, KyoSherlock
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kyo.groupadapter.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jianghui on 4/29/16.
 */
public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	public static final int VIEW_TYPE_RED = 0;
	public static final int VIEW_TYPE_PURPLE = 1;

	@NonNull
	private final List<Item> items;
	private final View.OnClickListener onClickListener;

	public FirstAdapter(@NonNull List<Item> items, View.OnClickListener onClickListener) {
		if (items == null) {
			throw new NullPointerException();
		}
		this.items = items;
		this.onClickListener = onClickListener;
	}

	public void addItem(int position, Item item) {
		Log.e("kyo", "addItem position:" + position);
		items.add(position, item);
		this.notifyItemInserted(position);
	}

	@Override
	public int getItemViewType(int position) {
		return items.get(position).viewType;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		if (viewType == VIEW_TYPE_RED) {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_red, parent, false);
			RedViewHolder viewHolder = new RedViewHolder(itemView);
			itemView.setOnClickListener(onClickListener);
			return viewHolder;
		} else {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_purple, parent, false);
			PurpleViewHolder viewHolder = new PurpleViewHolder(itemView);
			itemView.setOnClickListener(onClickListener);
			return viewHolder;
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof RedViewHolder) {
			RedViewHolder viewHolder = (RedViewHolder) holder;
			viewHolder.itemView.setTag(items.get(position).text);
			viewHolder.textView.setText(items.get(position).text);
		} else {
			PurpleViewHolder viewHolder = (PurpleViewHolder) holder;
			viewHolder.itemView.setTag(items.get(position).text);
			viewHolder.textView.setText(items.get(position).text);
		}
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class RedViewHolder extends RecyclerView.ViewHolder {

		private TextView textView;

		public RedViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.text);
		}
	}

	static class PurpleViewHolder extends RecyclerView.ViewHolder {
		private TextView textView;

		public PurpleViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.text);
		}
	}

	static class Item {
		String text;
		int viewType;

		public Item(String text) {
			this(text, VIEW_TYPE_RED);
		}

		public Item(String text, int viewType) {
			this.text = text;
			this.viewType = viewType;
		}
	}
}