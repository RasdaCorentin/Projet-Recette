package com.yoann_bezard.dao.imp;

import com.yoann_bezard.dao.DaoFactory;
import com.yoann_bezard.dao.entiites.Recette;
import com.yoann_bezard.dao.interfaces.RecetteInterface;
import java.util.List;

public class RecetteDaoImp implements RecetteInterface {
    
    private DaoFactory daoFactory;
    
    public RecetteDaoImp( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Recette> getListeRecette() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recette findRecette(Recette recette) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recette createRecette(Recette recette) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recette updateRecette(Recette recette) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteRecette(Recette recette) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}