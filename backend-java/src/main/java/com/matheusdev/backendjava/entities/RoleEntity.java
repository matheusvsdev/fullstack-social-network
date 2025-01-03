package com.matheusdev.backendjava.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Document(collection = "roles")
public class RoleEntity implements GrantedAuthority {

	@Id
	private String objectId;
	private String authority;

	public RoleEntity() {
	}

	public RoleEntity(String objectId, String authority) {
		this.objectId = objectId;
		this.authority = authority;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;

		RoleEntity role = (RoleEntity) o;
		return Objects.equals(authority, role.authority);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(authority);
	}
}
