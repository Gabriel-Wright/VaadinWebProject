package com.gabeWebTest.webTest.views.dashboard;

import com.gabeWebTest.webTest.data.Tag;

import java.util.Set;

public interface TagFilterListener {
    void onTagFilterChanged(Set<Tag> selectedTags);
}
