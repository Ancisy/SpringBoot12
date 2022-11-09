package com.example.spring_security_login.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Integer id;
    @Column (name = "account")
    private String taiKhoan;
    @Column (name = "password")
    private String matKhau;
    @Column (name = "email")
    private String email;
    @Column (name = "name")
    private String hoten;
    @Column (name = "phone")
    private String soDienThoai;
    @Column (name = "address")
    private String diachi;
    @Column (name = "active")
    private boolean isActive;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;
    @PrePersist
    public void prePersist() {
        this.address.setDiachi("null");
    }
}
