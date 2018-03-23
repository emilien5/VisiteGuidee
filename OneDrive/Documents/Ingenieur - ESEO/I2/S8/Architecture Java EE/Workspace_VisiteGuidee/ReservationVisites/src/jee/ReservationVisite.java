
package jee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour reservationVisite complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservationVisite"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idClient" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idReservation" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idVisite" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nombrePlace" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="paiementEffectue" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationVisite", propOrder = {
    "idClient",
    "idReservation",
    "idVisite",
    "nombrePlace",
    "paiementEffectue"
})
public class ReservationVisite {

    protected int idClient;
    protected int idReservation;
    protected int idVisite;
    protected int nombrePlace;
    protected boolean paiementEffectue;

    /**
     * Obtient la valeur de la propri�t� idClient.
     * 
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * D�finit la valeur de la propri�t� idClient.
     * 
     */
    public void setIdClient(int value) {
        this.idClient = value;
    }

    /**
     * Obtient la valeur de la propri�t� idReservation.
     * 
     */
    public int getIdReservation() {
        return idReservation;
    }

    /**
     * D�finit la valeur de la propri�t� idReservation.
     * 
     */
    public void setIdReservation(int value) {
        this.idReservation = value;
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
     * Obtient la valeur de la propri�t� nombrePlace.
     * 
     */
    public int getNombrePlace() {
        return nombrePlace;
    }

    /**
     * D�finit la valeur de la propri�t� nombrePlace.
     * 
     */
    public void setNombrePlace(int value) {
        this.nombrePlace = value;
    }

    /**
     * Obtient la valeur de la propri�t� paiementEffectue.
     * 
     */
    public boolean isPaiementEffectue() {
        return paiementEffectue;
    }

    /**
     * D�finit la valeur de la propri�t� paiementEffectue.
     * 
     */
    public void setPaiementEffectue(boolean value) {
        this.paiementEffectue = value;
    }

}
