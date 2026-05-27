package br.edu.iftm.edumetrics.model;

public class DesempenhoDTO {

    private Long estudanteId;
    private Double media;
    private Integer totalNotas;

    public DesempenhoDTO() {
    }

    public DesempenhoDTO(Long estudanteId, Double media, Integer totalNotas) {
        this.estudanteId = estudanteId;
        this.media = media;
        this.totalNotas = totalNotas;
    }

    public Long getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(Long estudanteId) {
        this.estudanteId = estudanteId;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public void setTotalNotas(Integer totalNotas) {
        this.totalNotas = totalNotas;
    }
}
