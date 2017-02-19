using IagoMoreira.ProjetoDDD.Application.Interfaces;
using IagoMoreira.ProjetoDDD.Infra.Data.Interfaces;
using Microsoft.Practices.ServiceLocation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Application
{
    public class AppServiceBase : IAppServiceBase
    {
        private IUnitOfWork _uow;
        public void BeginTransaction()
        {
            _uow = ServiceLocator.Current.GetInstance<IUnitOfWork>();
            _uow.BeginTransaction();
        }

        public void Commit()
        {
            _uow.SaveChanges();
        }
    }
}
