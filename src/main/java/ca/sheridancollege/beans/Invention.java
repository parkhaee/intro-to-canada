package ca.sheridancollege.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Invention implements Serializable {
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	public Country country;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String inventionName;
	@NonNull
	private String inventor;

	@NonNull
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime inventDateTime;
}
