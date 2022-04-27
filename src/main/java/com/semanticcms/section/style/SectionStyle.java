/*
 * semanticcms-section-style - Default style for sections nested within SemanticCMS pages and elements.
 * Copyright (C) 2016, 2017, 2019, 2020, 2021, 2022  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-section-style.
 *
 * semanticcms-section-style is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-section-style is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-section-style.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.semanticcms.section.style;

import com.aoapps.web.resources.registry.Group;
import com.aoapps.web.resources.registry.Style;
import com.aoapps.web.resources.servlet.RegistryEE;
import com.semanticcms.core.renderer.html.HtmlRenderer;
import com.semanticcms.section.model.SectioningContent;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Registers the styles for sections in RegistryEE and HtmlRenderer.")
public class SectionStyle implements ServletContextListener {

  public static final Group.Name RESOURCE_GROUP = new Group.Name("semanticcms-section-style");

  // TODO: Change to Group.Name once we have group-level ordering
  public static final Style SEMANTICCMS_SECTION = new Style("/semanticcms-section-style/semanticcms-section.css");

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ServletContext servletContext = event.getServletContext();

    // Add our CSS file
    RegistryEE.Application.get(servletContext)
        .activate(RESOURCE_GROUP)// TODO: Activate as-needed
        .getGroup(RESOURCE_GROUP)
        .styles
        .add(SEMANTICCMS_SECTION);

    // Add list item CSS class
    HtmlRenderer.getInstance(servletContext).addListItemCssClass(SectioningContent.class, "semanticcms-section-list-item");
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    // Do nothing
  }
}
