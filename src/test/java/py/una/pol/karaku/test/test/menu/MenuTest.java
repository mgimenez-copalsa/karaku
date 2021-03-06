/*-
 * Copyright (c)
 *
 * 		2012-2014, Facultad Politécnica, Universidad Nacional de Asunción.
 * 		2012-2014, Facultad de Ciencias Médicas, Universidad Nacional de Asunción.
 * 		2012-2013, Centro Nacional de Computación, Universidad Nacional de Asunción.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package py.una.pol.karaku.test.test.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import py.una.pol.karaku.exception.KarakuRuntimeException;
import py.una.pol.karaku.log.Log;
import py.una.pol.karaku.menu.client.AbstractMenuProvider;
import py.una.pol.karaku.menu.client.MenuHelper;
import py.una.pol.karaku.menu.schemas.Menu;
import py.una.pol.karaku.menu.server.MenuServerLogic;
import py.una.pol.karaku.test.base.BaseTest;
import py.una.pol.karaku.test.configuration.BaseTestConfiguration;
import py.una.pol.karaku.test.util.TestI18nHelper;
import py.una.pol.karaku.test.util.TestPropertiesUtil;

/**
 * 
 * @author Arturo Volpe
 * @since 2.2.8
 * @version 1.0 Oct 17, 2013
 * 
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class MenuTest extends BaseTest {

    @Configuration
    static class ContextConfiguration extends BaseTestConfiguration {

        @Bean
        MenuServerLogic menuServerLogic() {

            return new MenuServerLogic();
        }

        @Bean
        MenuHelper menuHelper() {

            return new MenuHelper();
        }

        @Bean
        TestMenuProvider testMenuHelper() {

            return new TestMenuProvider();
        }
    }

    @Log
    private Logger log;

    /**
 *
 */
    @Autowired
    MenuServerLogic menuServerLogic;

    @Autowired
    TestI18nHelper i18nHelper;

    @Autowired
    TestPropertiesUtil propertiesUtil;

    @Autowired
    MenuHelper mh;

    @Autowired
    TestMenuProvider testMenuProvider;

    private Menu m1;

    private Menu m12;

    private Menu m11;

    private Menu m122;

    private Menu m121;

    @Before
    public void addEntriesI18() {

        i18nHelper.addString("1", "UNO");
        i18nHelper.addString("1.1", "UNO.UNO");
        i18nHelper.addString("1.2", "UNO.DOS");
        i18nHelper.addString("1.2.1", "UNO.DOS.UNO");
        i18nHelper.addString("1.2.2", "UNO.DOS.DOS");
        i18nHelper.addString("TEST_STRING", "test");

    }

    @Before
    public void initialize() {

        m1 = m(null, "1", 1, null);
        m12 = m(m1, "1.2", 2, null);
        m11 = m(m1, "1.1", 1, "/views/caso3/list.xhtml\n");
        m121 = m(m12, "1.2.1", 1, "/views/caso4/report.xhtml");
        m122 = m(m12, "1.2.2", 2, "/views/caso5/list.xhtml");
    }

    @Before
    public void setProperties() {

        propertiesUtil.put("application.appUrlPlaceHolder", "base");
    }

    /**
     * Si este test falla, verificar el test {@link #converTest()} que convierte
     * un formato viejo al nuevo.
     */
    @Test
    public void getMenuTest() {

        try {
            Menu m = menuServerLogic.getCurrentSystemMenu().get(0);
            assertNotNull(m.getItems());
            assertFalse(m.getOrder() == 0);
        } catch (KarakuRuntimeException kre) {
            if (kre.getCause() instanceof FileNotFoundException) {
                fail("El menu del sistema actual no existe");
            }
            if (kre.getCause() instanceof JAXBException) {
                fail("El menu del sistema esta mal formateado");
            }
            if (kre.getCause() instanceof UnsupportedEncodingException) {
                fail("El menu del sistema tiene un encoding incorrecto");
            }

        }
    }

    @Test
    public void buildMenuTest() {

        menuServerLogic.configMenu(m1);

        assertEquals("No ordena (1er level)", Arrays.asList(m11, m12),
                m1.getItems());

        assertEquals("No ordena (2do level)", Arrays.asList(m121, m122),
                m12.getItems());
        mh.createAlias();
    }

    @Test
    public void internationalizationTest() {

        menuServerLogic.configMenu(m1);
        assertEquals("UNO", m1.getName());
        assertEquals("UNO.UNO", m11.getName());
        assertEquals("UNO.DOS", m12.getName());
        assertEquals("UNO.DOS.DOS", m122.getName());
    }

    @Test
    public void testMenuHelperFather() {

        Menu root = menuServerLogic.configMenu(m1);
        testMenuProvider.setRoot(root);
        mh.createAlias();

        assertEquals(m1, mh.getFather(m11));
        assertEquals(m12, mh.getFather(m122));
        assertEquals(null, mh.getFather(m1));
        assertEquals(m1, mh.getFather(m12));

    }

    @Test
    public void testMenuHelperGetByUri() {

        Menu root = menuServerLogic.configMenu(m1);
        testMenuProvider.setRoot(root);
        mh.createAlias();

        assertEquals(m11, mh.getMenuByUrl("/views/caso3/abm.xhtml"));
        assertEquals(m11, mh.getMenuByUrl("/views/caso3/list.xhtml"));
        assertEquals(m121, mh.getMenuByUrl("/views/caso4/report.xhtml"));
        assertEquals(m122, mh.getMenuByUrl("/views/caso5/abm.xhtml"));
        assertEquals(m122, mh.getMenuByUrl("/views/caso5/list.xhtml"));

    }

    // /////////////////////////////
    // MÉTODO Y CLASES UTILITARIAS//
    // /////////////////////////////

    public Menu m(Menu father, String id, int order, String uri) {

        Menu toRet = new Menu();
        toRet.setId(id);
        toRet.setOrder(order);
        toRet.setName(id);
        toRet.setUrl(uri);
        if (father != null) {
            father.getItems().add(toRet);
        }
        return toRet;
    }

    static class TestMenuProvider extends AbstractMenuProvider {

        List<Menu> roots;

        public void setRoot(Menu root) {

            this.roots = new ArrayList<Menu>();
            this.roots.add(root);
            notifyMenuRebuild(this.roots);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Menu> getMenu() {

            return roots;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Menu> getLocalMenu() {

            return roots;
        }

    }

}
