using IagoMoreira.ProjetoDDD.Infra.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Web;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Context
{
    public class ContextManager : IContextManager
    {
        private const string ContextKey = "ContextManagerKey";

        public IDbContext GetContext()
        {
            if(HttpContext.Current.Items[ContextKey] == null)
            {
                HttpContext.Current.Items[ContextKey] = new InfnetDbContext();
            }
            return HttpContext.Current.Items[ContextKey] as IDbContext;
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public DbEntityEntry<T> Entry<T>(T entity) where T : class
        {
            throw new NotImplementedException();
        }

        public int SaveChanges()
        {
            throw new NotImplementedException();
        }
    }
}
