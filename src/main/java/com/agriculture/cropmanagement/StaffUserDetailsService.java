package com.agriculture.cropmanagement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbc;

    public StaffUserDetailsService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        try {
            return jdbc.queryForObject(
                "SELECT username, password_hash FROM staff_users WHERE LOWER(username) = LOWER(?)",
                (rs, rowNum) -> User.withUsername(rs.getString("username"))
                    .password(rs.getString("password_hash"))
                    .roles("STAFF")
                    .build(),
                username
            );
        } catch (org.springframework.dao.EmptyResultDataAccessException ex) {
            throw new UsernameNotFoundException("Staff account not found");
        }
    }
}
