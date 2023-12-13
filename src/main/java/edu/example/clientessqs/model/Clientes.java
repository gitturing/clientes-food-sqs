package edu.example.clientessqs.model;

public record Clientes(
         String nombre,
         String documento,
         String email,
         String direccion,
         String telefono,
         String fecha_fegistro
) {}
