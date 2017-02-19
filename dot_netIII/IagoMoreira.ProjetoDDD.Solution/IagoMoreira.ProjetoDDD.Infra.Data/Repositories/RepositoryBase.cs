using IagoMoreira.ProjetoDDD.Domain.Interfaces.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Linq.Expressions;
using System.Data.Entity;
using IagoMoreira.ProjetoDDD.Infra.Data.Context;
using IagoMoreira.ProjetoDDD.Infra.Data.Interfaces;
using Microsoft.Practices.ServiceLocation;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Repositories
{
    public class RepositoryBase<TEntity> : IRepositoryBase<TEntity> where TEntity : class
    {

        protected IDbSet<TEntity> set;

        protected readonly IDbContext db;
        private readonly ContextManager _contextManager =
            ServiceLocator.Current.GetInstance<IContextManager>() as ContextManager;
  
        public RepositoryBase()
        {
            db = _contextManager.GetContext();
            set = db.Set<TEntity>();
        }

        public void Add(TEntity obj)
        {
            set.Add(obj);
        }

        public void Dispose()
        {
            db.Dispose();
            GC.SuppressFinalize(this);
        }

        public IEnumerable<TEntity> Find(Expression<Func<TEntity, bool>> predicate)
        {
            return set.Where(predicate);
        }

        public IEnumerable<TEntity> GetAll()
        {
            return set.ToList();
        }

        public TEntity GetById(int id)
        {
            return set.Find(id);
        }

        public void Remove(TEntity obj)
        {
            set.Remove(obj);
        }

        public void Update(TEntity obj)
        {
            db.Entry(obj).State = EntityState.Modified;
        }
    }
}
