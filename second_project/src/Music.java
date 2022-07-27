import java.io.InputStream;

public class Music {
	private int music_id;
	private String title;
	private String artist;
	private String genre;
	private InputStream mp3;
	private int recent_play;
	private int total_play;
	private int now_rank;
	private int past_rank;

	public Music(int music_id, String title, String artist, String genre, InputStream mp3, int recent_play,
			int total_play, int now_rank, int past_rank) {
		super();
		this.music_id = music_id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.mp3 = mp3;
		this.recent_play = recent_play;
		this.total_play = total_play;
		this.now_rank = now_rank;
		this.past_rank = past_rank;
	}

	public Music(String title, String artist, String genre) {
		super();
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}

	public int getMusic_id() {
		return music_id;
	}

	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}

	public InputStream getMp3() {
		return mp3;
	}

	public void setMp3(InputStream mp3) {
		this.mp3 = mp3;
	}

	public int getRecent_play() {
		return recent_play;
	}

	public void setRecent_play(int recent_play) {
		this.recent_play = recent_play;
	}

	public int getTotal_play() {
		return total_play;
	}

	public void setTotal_play(int total_play) {
		this.total_play = total_play;
	}

	public int getNow_rank() {
		return now_rank;
	}

	public void setNow_rank(int now_rank) {
		this.now_rank = now_rank;
	}

	public int getPast_rank() {
		return past_rank;
	}

	public void setPast_rank(int past_rank) {
		this.past_rank = past_rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
