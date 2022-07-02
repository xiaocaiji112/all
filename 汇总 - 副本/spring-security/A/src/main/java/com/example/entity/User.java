package com.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private String username;
    private String password;
    private ArrayList<String> permissions;

    @JSONField(serialize = false)//不会呗序列化
    private List<SimpleGrantedAuthority> permissionsList ;

    public User(String username, String password, ArrayList<String> permissions) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permissions=" + permissions +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将permissions中的string类型的权限封装成她的实现类SimpleGrantedAuthority
        if (this.permissionsList != null){
             return this.permissionsList;
        }
        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            list.add(authority);
        }
        this.permissionsList = list;
        return permissionsList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    @JSONField(serialize = false)//不会呗序列化
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)//不会呗序列化
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JSONField(serialize = false)//不会呗序列化
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)//不会呗序列化
    public boolean isEnabled() {
        return true;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }
}
