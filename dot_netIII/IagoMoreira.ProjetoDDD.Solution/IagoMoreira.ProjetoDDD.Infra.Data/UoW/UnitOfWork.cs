using IagoMoreira.ProjetoDDD.Infra.Data.Context;
using IagoMoreira.ProjetoDDD.Infra.Data.Interfaces;
using Microsoft.Practices.ServiceLocation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Infra.Data.UoW
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly IDbContext _dbContext;
        private readonly ContextManager _contextManager = 
            ServiceLocator.Current.GetInstance<IContextManager>() as ContextManager;

        private bool _disposed;

        public UnitOfWork()
        {
            _dbContext = _contextManager.GetContext();
        }

        public void BeginTransaction()
        {
            _disposed = false;
        }

        public void SaveChanges()
        {
            _dbContext.SaveChanges();
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        protected virtual void Dispose(bool disposing)
        {
            if(!_disposed)
            {
                if(disposing)
                {
                    _dbContext.Dispose();
                }
            }
        }
    }
}
