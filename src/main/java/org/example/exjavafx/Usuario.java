package org.example.exjavafx;

import lombok.Data;

@Data
public class Usuario {
    private String correo;
    private String plataforma;
    private Boolean admin;
    private Integer version;
    private String date;

    public Usuario() {}
    public Usuario(String correo, String plataforma, Boolean admin, Integer version, String date) {
        this.correo = correo;
        this.plataforma = plataforma;
        this.admin = admin;
        this.version = version;
        this.date = date;
    }


}
