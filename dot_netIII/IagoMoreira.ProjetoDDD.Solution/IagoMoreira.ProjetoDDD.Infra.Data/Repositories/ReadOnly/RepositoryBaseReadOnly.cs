using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Configuration;
using System.Data.SqlClient;

namespace IagoMoreira.ProjetoDDD.Infra.Data.Repositories.ReadOnly
{
    public class RepositoryBaseReadOnly
    {
        public IDbConnection Connection
        {
            get
            {
                return new SqlConnection(ConfigurationManager
                    .ConnectionStrings["InfnetDbContext"].ConnectionString);
            }
        }
    }
}
