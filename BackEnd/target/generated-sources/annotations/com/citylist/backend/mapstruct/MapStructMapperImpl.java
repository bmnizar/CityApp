package com.citylist.backend.mapstruct;

import com.citylist.backend.entities.ApplicationRole;
import com.citylist.backend.entities.ApplicationUser;
import com.citylist.backend.entities.City;
import com.citylist.backend.rest.dto.ApplicationRoleDTO;
import com.citylist.backend.rest.dto.ApplicationUserDTO;
import com.citylist.backend.rest.dto.CityDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-02T17:41:03+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public ApplicationUserDTO applicationUserEntityToDTO(ApplicationUser applicationUser) {
        if ( applicationUser == null ) {
            return null;
        }

        ApplicationUserDTO applicationUserDTO = new ApplicationUserDTO();

        applicationUserDTO.setUsername( applicationUser.getUsername() );
        applicationUserDTO.setPassword( applicationUser.getPassword() );
        applicationUserDTO.setApplicationRoles( applicationRoleCollectionToApplicationRoleDTOCollection( applicationUser.getApplicationRoles() ) );

        return applicationUserDTO;
    }

    @Override
    public ApplicationUser applicationUserDTOToEntity(ApplicationUserDTO applicationUser) {
        if ( applicationUser == null ) {
            return null;
        }

        ApplicationUser applicationUser1 = new ApplicationUser();

        applicationUser1.setUsername( applicationUser.getUsername() );
        applicationUser1.setPassword( applicationUser.getPassword() );
        applicationUser1.setApplicationRoles( applicationRoleDTOCollectionToApplicationRoleCollection( applicationUser.getApplicationRoles() ) );

        return applicationUser1;
    }

    @Override
    public CityDTO convertCityTOCtiyDTO(City city) {
        if ( city == null ) {
            return null;
        }

        CityDTO cityDTO = new CityDTO();

        if ( city.getId() != null ) {
            cityDTO.setId( city.getId().intValue() );
        }
        cityDTO.setName( city.getName() );
        byte[] image = city.getImage();
        if ( image != null ) {
            cityDTO.setImage( Arrays.copyOf( image, image.length ) );
        }

        return cityDTO;
    }

    @Override
    public List<CityDTO> convertListCityTOCtiyDTO(List<City> city) {
        if ( city == null ) {
            return null;
        }

        List<CityDTO> list = new ArrayList<CityDTO>( city.size() );
        for ( City city1 : city ) {
            list.add( convertCityTOCtiyDTO( city1 ) );
        }

        return list;
    }

    protected Collection<ApplicationUserDTO> applicationUserCollectionToApplicationUserDTOCollection(Collection<ApplicationUser> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<ApplicationUserDTO> collection1 = new ArrayList<ApplicationUserDTO>( collection.size() );
        for ( ApplicationUser applicationUser : collection ) {
            collection1.add( applicationUserEntityToDTO( applicationUser ) );
        }

        return collection1;
    }

    protected ApplicationRoleDTO applicationRoleToApplicationRoleDTO(ApplicationRole applicationRole) {
        if ( applicationRole == null ) {
            return null;
        }

        ApplicationRoleDTO applicationRoleDTO = new ApplicationRoleDTO();

        applicationRoleDTO.setName( applicationRole.getName() );
        applicationRoleDTO.setApplicationUsers( applicationUserCollectionToApplicationUserDTOCollection( applicationRole.getApplicationUsers() ) );

        return applicationRoleDTO;
    }

    protected Collection<ApplicationRoleDTO> applicationRoleCollectionToApplicationRoleDTOCollection(Collection<ApplicationRole> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<ApplicationRoleDTO> collection1 = new ArrayList<ApplicationRoleDTO>( collection.size() );
        for ( ApplicationRole applicationRole : collection ) {
            collection1.add( applicationRoleToApplicationRoleDTO( applicationRole ) );
        }

        return collection1;
    }

    protected Collection<ApplicationUser> applicationUserDTOCollectionToApplicationUserCollection(Collection<ApplicationUserDTO> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<ApplicationUser> collection1 = new ArrayList<ApplicationUser>( collection.size() );
        for ( ApplicationUserDTO applicationUserDTO : collection ) {
            collection1.add( applicationUserDTOToEntity( applicationUserDTO ) );
        }

        return collection1;
    }

    protected ApplicationRole applicationRoleDTOToApplicationRole(ApplicationRoleDTO applicationRoleDTO) {
        if ( applicationRoleDTO == null ) {
            return null;
        }

        ApplicationRole applicationRole = new ApplicationRole();

        applicationRole.setName( applicationRoleDTO.getName() );
        applicationRole.setApplicationUsers( applicationUserDTOCollectionToApplicationUserCollection( applicationRoleDTO.getApplicationUsers() ) );

        return applicationRole;
    }

    protected Collection<ApplicationRole> applicationRoleDTOCollectionToApplicationRoleCollection(Collection<ApplicationRoleDTO> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<ApplicationRole> collection1 = new ArrayList<ApplicationRole>( collection.size() );
        for ( ApplicationRoleDTO applicationRoleDTO : collection ) {
            collection1.add( applicationRoleDTOToApplicationRole( applicationRoleDTO ) );
        }

        return collection1;
    }
}
