package uam.admision.controlguias.domain;

import javax.persistence.*;

@Entity
@Table(name = "estado", schema = "public", catalog = "controlguias")
public class EstadoEntity {

    private Integer idEstado;
    private String describeCorto;
    private String describeLargo;

    @Id
    @Column(name = "id_estado", nullable = false)
    public Integer getIdEstado() {
        return idEstado;
    }


    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    @Basic
    @Column(name = "describe_corto", nullable = false, length = 15)
    public String getDescribeCorto() {
        return describeCorto;
    }

    public void setDescribeCorto(String describeCorto) {
        this.describeCorto = describeCorto;
    }

    @Basic
    @Column(name = "describe_largo", nullable = false, length = 30)
    public String getDescribeLargo() {
        return describeLargo;
    }

    public void setDescribeLargo(String describeLargo) {
        this.describeLargo = describeLargo;
    }


}
