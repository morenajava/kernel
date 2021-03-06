/*
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.container.jmx.support;

import org.exoplatform.management.ManagementAware;
import org.exoplatform.management.ManagementContext;
import org.exoplatform.management.annotations.Managed;
import org.exoplatform.management.jmx.annotations.NameTemplate;
import org.exoplatform.management.jmx.annotations.Property;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
@Managed
@NameTemplate(@Property(key = "object", value = "ManagedManagementAware"))
public class ManagedManagementAware implements ManagementAware
{

   public ManagementContext context;

   private int count = 0;

   public AssertionError failure;

   public ManagedDependent foo = new ManagedDependent("Foo");

   public void setContext(ManagementContext context)
   {

      //
      if (count == 0)
      {
         if (context == null)
         {
            failure = new AssertionError();
         }
         this.context = context;
         this.context.register(foo);
         count = 1;
      }
      else if (count == 1)
      {
         if (context != null)
         {
            failure = new AssertionError();
         }
         this.context.unregister(foo);
      }
      else
      {
         failure = new AssertionError();
      }
   }
}
