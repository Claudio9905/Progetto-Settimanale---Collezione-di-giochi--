package claudiopostiglione.interfaces;

import claudiopostiglione.entities.Videogiochi;

import java.util.List;

public interface RichiesteCollezioneVideoGames {
    public void elementoAggiunto(List<Videogiochi> videogiochiList, Videogiochi nuovoVideoGioco);
    public void ricercaId(List<Videogiochi> videogiochiList, Long idGiocoInserito);
    public void ricercaPerPrezzo(List<Videogiochi> videogiochiList, double prezzoInserito);
    public boolean rimozioneGioco(List<Videogiochi> videogiochiList, Long idGiocoInserito);
    public void updateGioco(List<Videogiochi> videogiochiList, Long idGioco);
    public void statisticheCollezione(List<Videogiochi> videogiochiList);
}

