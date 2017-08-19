package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jmartin
 */
public class equipo {
    
    private String serie;
    private int id;
    private boolean existe;

    public equipo (String serie,int id)
    {
          this.serie= serie;
          this.id = id;
          this.existe=false;
    }
    
    
    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @return the Existencia
     */
    public boolean getExiste() {
        return existe;
    }
    
    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @param existencia the id to set
     */
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    /**
     *
     */
    @Override
    public String  toString()
    {
        String s;
        if (this.existe)
            s="Existe";
        else
            s="No existe";
        
        return ("Equipo :" + this.serie + "    Destino :  " + this.id + "   Status : " + s);
    }
}
