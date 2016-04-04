/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbv.controller;

import hbv.view.MockDisplay;
import hbv.view.MockDisplay2;
import hbv.view.MockDisplay3;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AndriValur
 */
public class SearchManagerTest {
    
    
    
    public SearchManagerTest() {
        
    }
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of createList method, of class SearchManager.
     */
    @Test
    public void testCreateList() {
        MockDisplay view = new MockDisplay();
        SearchManager instance = new SearchManager(view);
        instance.createList();
        // TODO review the generated test code and remove the default call to fail.
        
        assertNotNull(instance.getTours());
    }
    
    @Test
    public void testCreateListInvalidVar(){
        MockDisplay2 view = new MockDisplay2();
        SearchManager instance = new SearchManager(view);
        instance.createList();
        
        assertTrue(instance.isToursEmpty());
    }
    
    @Test
    public void testCreateListNegativeSeatsAvailable(){
        MockDisplay3 view = new MockDisplay3();
        SearchManager instance = new SearchManager(view);
        instance.createList();
        
        assertTrue(instance.isToursEmpty());
    }

    
}
