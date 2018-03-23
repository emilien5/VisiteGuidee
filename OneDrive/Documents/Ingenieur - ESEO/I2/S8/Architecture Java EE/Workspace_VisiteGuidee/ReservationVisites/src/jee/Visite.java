
package jee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour visite complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="visite"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateVisite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idVisite" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="prixVisite" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="typeVisite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ville" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visite", propOrder = {
    "dateVisite",
    "idVisite",
    "prixVisite",
    "typeVisite",
    "ville"
})
public class Visite {

    protected String dateVisite;
    protected int idVisite;
    protected int prixVisite;
    protected String typeVisite;
    protected String ville;

    /**
     * Obtient la valeur de la propri�t� dateVisite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateVisite() {
        return dateVisite;
    }

    /**
     * D�finit la valeur de la propri�t� dateVisite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateVisite(String value) {
        this.dateVisite = value;
    }

    /**
     * Obtient la valeur de la propri�t� idVisite.
     * 
     */
    public int getIdVisite() {
        return idVisite;
    }

    /**
     * D�finit la valeur de la propri�t� idVisite.
     * 
     */
    public void setIdVisite(int value) {
        this.idVisite = value;
    }

    /**
     * Obtient la valeur de la propri�t� prixVisite.
     * 
     */
    public int getPrixVisite() {
        return prixVisite;
    }

    /**
     * D�finit la valeur de la propri�t� prixVisite.
     * 
     */
    public void setPrixVisite(int value) {
        this.prixVisite = value;
    }

    /**
     * Obtient la valeur de la propri�t� typeVisite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeVisite() {
        return typeVisite;
    }

    /**
     * D�finit la valeur de la propri�t� typeVisite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeVisite(String value) {
        this.typeVisite = value;
    }

    /**
     * Obtient la valeur de la propri�t� ville.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVille() {
        return ville;
    }

    /**
     * D�finit la valeur de la propri�t� ville.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVille(String value) {
        this.ville = value;
    }

}
