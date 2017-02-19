using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Interfaces
{
    public interface IContextManager
    {
        IDbContext GetContext();
    }
}
