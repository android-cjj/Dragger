/*
* Copyright (C) 2015 Pedro Paulo de Amorim
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.github.ppamorim.dragger;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.InjectView;
import com.github.ppamorim.dragger.app.R;

public class ImageActivity extends AbstractActivity {

  private static final String CAN_ANIMATE = "can_animate";
  public static final String DRAG_POSITION = "drag_position";

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.dragger_view) DraggerView draggerView;

  @Override protected int getContentViewId() {
    return R.layout.activity_dragger;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    configToolbar();
    configIntents();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putBoolean(CAN_ANIMATE, draggerView.getCanAnimate());
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void onBackPressed() {
    draggerView.closeActivity();
  }

  private void configToolbar() {
    setSupportActionBar(toolbar);
    toolbar.setTitle(getResources().getString(R.string.app_name));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void configIntents() {
    draggerView.setDraggerPosition((DraggerPosition) getIntent().getSerializableExtra(DRAG_POSITION));
  }

}