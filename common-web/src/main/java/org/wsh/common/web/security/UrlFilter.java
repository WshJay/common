package org.wsh.common.web.security;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class UrlFilter implements Serializable{

	private static final long serialVersionUID = -3703253415408784184L;
	
	@Getter
	@Setter
	private Long id;
	
	//url名称/描述
	@Getter
	@Setter
    private String name; 
	
    //地址
	@Getter
	@Setter
    private String url; 
	
    //所需要的角色，可省略
	@Getter
	@Setter
    private String roles;
	
    //所需要的权限，可省略
	@Getter
	@Setter
    private String permissions; 
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlFilter urlFilter = (UrlFilter) o;

        if (id != null ? !id.equals(urlFilter.id) : urlFilter.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UrlFilter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", roles='" + roles + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}

