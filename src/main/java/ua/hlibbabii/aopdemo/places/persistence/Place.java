package ua.hlibbabii.aopdemo.places.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.lang.Double;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;

@Entity
@Table(name = "PLACE")
@NamedQueries({
        @NamedQuery(name="getPlaceByName", query = "SELECT p from Place p where p.name LIKE :query"),
})
@SQLInsert(sql="INSERT into PLACE (googleId, longitude, latitude, name) values (?, ?, ?, ?) ON DUPLICATE KEY UPDATE")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    //@NotNull
    private String name;

    //@NotNull
    @Column(unique = true)
    @JsonIgnore
    private String googleId;

    //@NotNull
    private Double longitude;

   // @NotNull
    private Double latitude;

    public Place() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (!id.equals(place.id)) return false;
        if (!latitude.equals(place.latitude)) return false;
        if (!longitude.equals(place.longitude)) return false;
        if (!name.equals(place.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + longitude.hashCode();
        result = 31 * result + latitude.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
