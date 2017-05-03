/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2017 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.base.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.base.db.repo.*;
import com.axelor.apps.base.service.AddressService;
import com.axelor.apps.base.service.AddressServiceImpl;
import com.axelor.apps.base.service.BankService;
import com.axelor.apps.base.service.BankServiceImpl;
import com.axelor.apps.base.service.CompanyService;
import com.axelor.apps.base.service.CompanyServiceImpl;
import com.axelor.apps.base.service.DurationService;
import com.axelor.apps.base.service.DurationServiceImpl;
import com.axelor.apps.base.service.MailServiceBaseImpl;
import com.axelor.apps.base.service.ProductService;
import com.axelor.apps.base.service.ProductServiceImpl;
import com.axelor.apps.base.service.app.AppBaseService;
import com.axelor.apps.base.service.app.AppBaseServiceImpl;
import com.axelor.apps.base.service.app.AppService;
import com.axelor.apps.base.service.app.AppServiceImpl;
import com.axelor.apps.base.service.message.MailAccountServiceBaseImpl;
import com.axelor.apps.base.service.message.MessageServiceBaseImpl;
import com.axelor.apps.base.service.message.TemplateMessageServiceBaseImpl;
import com.axelor.apps.base.service.tax.AccountManagementService;
import com.axelor.apps.base.service.tax.AccountManagementServiceImpl;
import com.axelor.apps.base.service.tax.FiscalPositionService;
import com.axelor.apps.base.service.tax.FiscalPositionServiceImpl;
import com.axelor.apps.base.service.template.TemplateBaseService;
import com.axelor.apps.base.service.user.UserService;
import com.axelor.apps.base.service.user.UserServiceImpl;
import com.axelor.apps.base.service.weeklyplanning.WeeklyPlanningService;
import com.axelor.apps.base.service.weeklyplanning.WeeklyPlanningServiceImp;
import com.axelor.apps.message.service.MailAccountServiceImpl;
import com.axelor.apps.message.service.MailServiceMessageImpl;
import com.axelor.apps.message.service.MessageServiceImpl;
import com.axelor.apps.message.service.TemplateMessageServiceImpl;
import com.axelor.apps.message.service.TemplateService;

public class BaseModule extends AxelorModule {

    @Override
    protected void configure() {
        bind(AddressService.class).to(AddressServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(MessageServiceImpl.class).to(MessageServiceBaseImpl.class);
        bind(MailAccountServiceImpl.class).to(MailAccountServiceBaseImpl.class);
        bind(AccountManagementService.class).to(AccountManagementServiceImpl.class);
        bind(FiscalPositionService.class).to(FiscalPositionServiceImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(TemplateService.class).to(TemplateBaseService.class);
        bind(TemplateMessageServiceImpl.class).to(TemplateMessageServiceBaseImpl.class);
        bind(PartnerRepository.class).to(PartnerBaseRepository.class);
        bind(DurationRepository.class).to(DurationBaseRepository.class);
        bind(DurationService.class).to(DurationServiceImpl.class);
        bind(AppBaseService.class).to(AppBaseServiceImpl.class);
        bind(SequenceRepository.class).to(SequenceBaseRepository.class);
        bind(ProductRepository.class).to(ProductBaseRepository.class);
        bind(WeeklyPlanningService.class).to(WeeklyPlanningServiceImp.class);
        bind(MailServiceMessageImpl.class).to(MailServiceBaseImpl.class);
        bind(AddressRepository.class).to(AddressBaseRepository.class);
        bind(YearRepository.class).to(YearBaseRepository.class);
        bind(AppServiceImpl.class).to(AppBaseServiceImpl.class);
        bind(AppService.class).to(AppServiceImpl.class);
        bind(BankService.class).to(BankServiceImpl.class);
        bind(BankRepository.class).to(BankBaseRepository.class);
        bind(CompanyService.class).to(CompanyServiceImpl.class);
        bind(BankAddressRepository.class).to(BankAddressBaseRepository.class);
    }
}