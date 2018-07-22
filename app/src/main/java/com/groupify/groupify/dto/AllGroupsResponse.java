package com.groupify.groupify.dto;

import com.google.gson.annotations.Expose;

public class AllGroupsResponse {

	@Expose
	private Group[] groups;

	public Group[] getGroups() {
		return groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}
}
