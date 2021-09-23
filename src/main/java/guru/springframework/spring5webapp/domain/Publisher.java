package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String addressStreet;
    private String addressCity;
    private String addressZip;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<Book> books = new HashSet<>();

    public Publisher()
    {
    }

    public Publisher(String name, String addressStreet, String addressCity, String addressZip)
    {
        this.name = name;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressZip = addressZip;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddressStreet()
    {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet)
    {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity()
    {
        return addressCity;
    }

    public void setAddressCity(String addressCity)
    {
        this.addressCity = addressCity;
    }

    public String getAddressZip()
    {
        return addressZip;
    }

    public void setAddressZip(String addressZip)
    {
        this.addressZip = addressZip;
    }

    public Set<Book> getBooks()
    {
        return books;
    }

    public void setBooks(Set<Book> books)
    {
        this.books = books;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return id != null ? id.equals(publisher.id) : publisher.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressZip='" + addressZip + '\'' +
                '}';
    }
}
