using CommonServiceLocator.SimpleInjectorAdapter;
using IagoMoreira.ProjetoDDD.Application;
using IagoMoreira.ProjetoDDD.Application.Entities;
using IagoMoreira.ProjetoDDD.Application.Interfaces;
using IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository;
using IagoMoreira.ProjetoDDD.Domain.Interfaces.Services;
using IagoMoreira.ProjetoDDD.Domain.Services;
using IagoMoreira.ProjetoDDD.Infra.Data.Context;
using IagoMoreira.ProjetoDDD.Infra.Data.Interfaces;
using IagoMoreira.ProjetoDDD.Infra.Data.Repositories;
using IagoMoreira.ProjetoDDD.Infra.Data.UoW;
using Microsoft.Practices.ServiceLocation;
using SimpleInjector;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.CrossCutting
{
    public static class BootStrapper
    {
        public static void RegisterServices(Container container)
        {
            //Application
            container.RegisterPerWebRequest<IAlunoAppService, AlunoAppService>();
            container.RegisterPerWebRequest<IAppServiceBase, AppServiceBase>();

            //Domain
            container.RegisterPerWebRequest<IAlunoService, AlunoService>();

            //Infra.Data
            container.RegisterPerWebRequest<IAlunoRepository, AlunoRepository>();

            //Data Configuration
            container.RegisterPerWebRequest<IContextManager, ContextManager>();
            container.RegisterPerWebRequest<IDbContext, InfnetDbContext>();
            container.RegisterPerWebRequest<IUnitOfWork, UnitOfWork>();

            ServiceLocator.SetLocatorProvider(() => new SimpleInjectorServiceLocatorAdapter(container));
        }
    }
}
