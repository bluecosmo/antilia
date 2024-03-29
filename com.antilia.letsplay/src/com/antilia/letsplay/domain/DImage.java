package com.antilia.letsplay.domain;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Tuplizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antilia.hibernate.remote.TuplizerImpl;
import com.antilia.web.crud.Exclude;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name = "image")
@Tuplizer(impl=TuplizerImpl.class)
public class DImage implements java.io.Serializable, Comparable<DImage> {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(DImage.class);
	
	@Exclude
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name="image_seq",sequenceName="image_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "description", unique = false, nullable = false, length = 40)
	private String description;
	
	@Column(name = "mimetype", unique = false, nullable = false, length = 40)
	private String mimeType;

	@Column(name = "image", nullable = false)
	private Blob image;
	
	public DImage() {
	}

	public DImage(long id, String text) {
		this.id = id;
		setDescription(text);
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public int compareTo(DImage o) {
		return getDescription().compareTo(o.getDescription());
	}
	
	@Override
	public String toString() {
		return getDescription();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DImage) {
			return getId().equals(((DImage)obj).getId());
		}
		return false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String text) {
		if(text != null)			
			this.description = text.trim();
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
	public void setBytes(byte[] image) {
		if(image != null)
			this.image = Hibernate.createBlob(image);
	}
	
	public byte[] getBytes() {
		try {
			return toByteArrayImpl(this.image);
		} catch (SQLException e) {
			logger.error("getBytes", e);
		} catch (IOException e) {
			logger.error("getBytes", e);
		}
		return null;
	}
	
	private byte[] toByteArrayImpl(Blob fromImageBlob) throws SQLException, IOException {
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte buf[] = new byte[4000];
	    int dataSize;
	    InputStream is = fromImageBlob.getBinaryStream(); 

	    try {
	      while((dataSize = is.read(buf)) != -1) {
	        baos.write(buf, 0, dataSize);
	      }    
	    } finally {
	      if(is != null) {
	        is.close();
	      }
	    }
	    return baos.toByteArray();
	 }
}
