package br.edu.iftm.edumetrics.model;

public class DesempenhoDTO {

    private Long alunoId;
    private double mediaGeral;
    private int creditosAprovados;

    public DesempenhoDTO(Long alunoId, double mediaGeral, int creditosAprovados) {
        this.alunoId = alunoId;
        this.mediaGeral = mediaGeral;
        this.creditosAprovados = creditosAprovados;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public double getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }

    public int getCreditosAprovados() {
        return creditosAprovados;
    }

    public void setCreditosAprovados(int creditosAprovados) {
        this.creditosAprovados = creditosAprovados;
    }
}
