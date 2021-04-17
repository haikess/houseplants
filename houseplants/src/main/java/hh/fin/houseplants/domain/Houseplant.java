package hh.fin.houseplants.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Houseplant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String lightNeed;
	private String fertilizer;
	private String watering;
	private String dateOfPurchase;
	
	@ManyToOne
	@JoinColumn(name = "classificationid")
	
	@JsonIgnoreProperties ("houseplants") 

	private Classification classification;

	public Houseplant(String name, String lightNeed, String fertilizer, String watering, String dateOfPurchase, Classification classification) {
		super();
		this.name = name;
		this.lightNeed = lightNeed;
		this.fertilizer = fertilizer;
		this.watering = watering;
		this.dateOfPurchase = dateOfPurchase;
		this.classification = classification;
	}

	public Houseplant() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLightNeed() {
		return lightNeed;
	}

	public String getFertilizer() {
		return fertilizer;
	}

	public String getWatering() {
		return watering;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	
	public Classification getClassification() {
		return classification;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLightNeed(String lightNeed) {
		this.lightNeed = lightNeed;
	}

	public void setFertilizer(String fertilizer) {
		this.fertilizer = fertilizer;
	}

	public void setWatering(String watering) {
		this.watering = watering;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	@Override
	public String toString() {
		return "Houseplant [id=" + id + ", name=" + name + ", lightNeed=" + lightNeed + ", fertilizer=" + fertilizer
				+ ", watering=" + watering + ", dateOfPurchase=" + dateOfPurchase + ", classification=" + classification
				+ "]";
	}

	

}
