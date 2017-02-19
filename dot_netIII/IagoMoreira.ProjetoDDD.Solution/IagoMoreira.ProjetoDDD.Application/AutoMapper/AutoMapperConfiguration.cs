using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IagoMoreira.ProjetoDDD.Application.AutoMapper
{
    public static class AutoMapperConfiguration
    {
        public static void RegisterMappings()
        {
            Mapper.Initialize(cfg =>
                {
                    cfg.AddProfile<DomainToViewModelMappingProfile>();
                    cfg.AddProfile<ViewModelToDomainMappingProfile>();
                });
        }
    }
}
