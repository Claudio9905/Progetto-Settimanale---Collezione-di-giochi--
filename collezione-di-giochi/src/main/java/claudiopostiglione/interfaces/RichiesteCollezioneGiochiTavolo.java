package claudiopostiglione.interfaces;

import claudiopostiglione.entities.GiochiDaTavolo;


import java.util.List;

public interface RichiesteCollezioneGiochiTavolo {
    public void elementoAggiunto(List<GiochiDaTavolo> giochiDaTavoloList);
    public void ricercaId(List<GiochiDaTavolo> giochiDaTavoloList, GiochiDaTavolo nuovoGiocoDaTavolo);
    public void ricercaPerPrezzo(List<GiochiDaTavolo> giochiDaTavoloList, double prezzoInserito);
    public void rimozioneGioco(List<GiochiDaTavolo> giochiDaTavoloList, Long idGioco);
    public void updateGioco(List<GiochiDaTavolo> giochiDaTavoloList, Long idGioco);
    public void statisticheCollezione(List<GiochiDaTavolo> giochiDaTavoloList);
}
