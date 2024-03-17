package com.gabeWebTest.webTest.views.dashboard.tagDisplay;

import com.gabeWebTest.webTest.data.webPage.tags.Tag;

import java.util.Set;

public interface TagFilterListener {
    void onTagFilterChanged(Set<Tag> selectedTags);
}
