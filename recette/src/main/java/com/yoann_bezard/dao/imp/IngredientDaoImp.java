package com.yoann_bezard.dao.imp;

import com.yoann_bezard.dao.DaoFactory;
import com.yoann_bezard.dao.entiites.Ingredient;
import com.yoann_bezard.dao.interfaces.IngredientInterface;
import java.util.List;

public class IngredientDaoImp implements IngredientInterface {
    
    private DaoFactory daoFactory;
    
    public IngredientDaoImp( DaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Ingredient> getListeIngredient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient findIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}