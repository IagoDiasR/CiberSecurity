package com.monqui.van_go.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_timeline")
public class Timeline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Boolean segunda;
	private Boolean terca;
	private Boolean quarta;
	private Boolean quinta;
	private Boolean sexta;
	private Boolean sabado;
	private Boolean domingo;

	public Timeline() {
        // Inicializa os dias como false por padrão
        this.segunda = false;
        this.terca = false;
        this.quarta = false;
        this.quinta = false;
        this.sexta = false;
        this.sabado = false;
        this.domingo = false;
    }

    public Timeline(Long id, Boolean segunda, Boolean terca, Boolean quarta, Boolean quinta, Boolean sexta, Boolean sabado, Boolean domingo) {
        this.id = id;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.domingo = domingo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSegunda() {
		return segunda;
	}

	public void setSegunda(Boolean segunda) {
		this.segunda = segunda;
	}

	public Boolean getTerca() {
		return terca;
	}

	public void setTerca(Boolean terca) {
		this.terca = terca;
	}

	public Boolean getQuarta() {
		return quarta;
	}

	public void setQuarta(Boolean quarta) {
		this.quarta = quarta;
	}

	public Boolean getQuinta() {
		return quinta;
	}

	public void setQuinta(Boolean quinta) {
		this.quinta = quinta;
	}

	public Boolean getSexta() {
		return sexta;
	}

	public void setSexta(Boolean sexta) {
		this.sexta = sexta;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeline other = (Timeline) obj;
		return Objects.equals(id, other.id);
	}
	

}
